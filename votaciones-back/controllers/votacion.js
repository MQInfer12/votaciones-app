import Express from "express";
import { PrismaClient } from "@prisma/client";

const app = Express();
const prisma = new PrismaClient();

app.get("/votacion", async (req, res) => {
  try {
    const votacion = await prisma.votacion.findMany();
    res.json({
      data: votacion,
      message: "votacion obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener votacion",
      error: error.message
    })
  }
});


app.post("/votacion", async (req, res) => {
  try {
    const votacion = await prisma.votacion.create({
      data: req.body
    })
    res.json({
      data: votacion,
      message: "votacion agregado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al crear votacion",
      error: error.message
    })
  }
})

app.put("/votacion/:id", async (req, res) => {
  try {
    const votacion = await prisma.votacion.update({
      where: {
        id: Number(req.params.id)
      },
      data: req.body
    })
    res.json({
      data: votacion,
      message: "votacion actualizado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al actualizar votacion",
      error: error.message
    })
  }
})

app.delete("/votacion/:id", async (req, res) => {
  try {
    const votacion = await prisma.votacion.delete({
      where: {
        id: Number(req.params.id)
      }
    })
    res.json({
      data: votacion,
      message: "votacion eliminado"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al eliminar votacion",
      error: error.message
    })
  }
})
app.post("/votacion/:id",async(req,res)=>{
  try {
    const votacion=await prisma.votacion.findMany({
      where:{
        id:Number(req.params.id)
      }
    })
    res.json({
      data:votacion,
      message:"votacion obtenido correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message:"Error al obtener el votacion",
      error:message.error
    })
  }
})

app.get("/misvotaciones/:ci", async (req, res) => {
  try {
    const votacion = await prisma.votacion.findMany({
      where: {
        ci: +req.params.ci,
      },
    });
    res.json({
      data: votacion,
      message: "votacion obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener votacion",
      error: error.message
    })
  }
})

app.post("/crearvotacion", async (req, res) => {
  try {
    const votacion = await prisma.votacion.create({
      data: {
        ci: req.body.ci,
        nombre: req.body.nombre,
        Candidatos: {
          createMany: {
            data: req.body.candidatos.map(candidato => ({
              nombre: candidato
            }))
          }
        },
        Asignaciones: {
          createMany: {
            data: req.body.asignaciones.map(asignacion => ({
              ci: asignacion
            }))
          }
        }
      }
    })
    res.json({
      data: votacion,
      message: "votacion agregado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al crear votacion",
      error: error.message
    })
  }
})

app.get("/ver/:id", async (req, res) => {
  try {
    const { Candidatos, Asignaciones } = await prisma.votacion.findUnique({
      where: {
        id: Number(req.params.id)
      },
      include: {
        Candidatos: true,
        Asignaciones: true
      }
    });

    const totalAsignados = Asignaciones.length;
    const totalVotos = Asignaciones.filter(asignacion => !!asignacion.idCandidato).length;
    const data = Candidatos.map(candidato => {
      const votos = Asignaciones.reduce((suma, asignacion) => {
        if(asignacion.idCandidato === candidato.id) {
          return suma + 1;
        }
        return suma;
      }, 0);
      return {
        nombre: candidato.nombre,
        votos
      };
    })

    res.json({
      data: {
        totalAsignados,
        totalVotos,
        candidatos: data
      },
      message: "votacion obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener votacion",
      error: error.message
    })
  }
})

export default app;