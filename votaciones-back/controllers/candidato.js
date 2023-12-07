import Express from "express";
import { PrismaClient } from "@prisma/client";

const app = Express();
const prisma = new PrismaClient();

app.get("/candidato", async (req, res) => {
  try {
    const candidato = await prisma.candidato.findMany();
    res.json({
      data: candidato,
      message: "candidato obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener candidato",
      error: error.message
    })
  }
});


app.post("/candidato", async (req, res) => {
  try {
    const candidato = await prisma.candidato.create({
      data: req.body
    })
    res.json({
      data: candidato,
      message: "candidato agregado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al crear candidato",
      error: error.message
    })
  }
})

app.put("/candidato/:id", async (req, res) => {
  try {
    const candidato = await prisma.candidato.update({
      where: {
        id: Number(req.params.id)
      },
      data: req.body
    })
    res.json({
      data: candidato,
      message: "candidato actualizado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al actualizar candidato",
      error: error.message
    })
  }
})

app.delete("/candidato/:id", async (req, res) => {
  try {
    const candidato = await prisma.candidato.delete({
      where: {
        id: Number(req.params.id)
      }
    })
    res.json({
      data: candidato,
      message: "candidato eliminado"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al eliminar candidato",
      error: error.message
    })
  }
})
app.post("/candidato/:id",async(req,res)=>{
  try {
    const candidato=await prisma.candidato.findMany({
      where:{
        id:Number(req.params.id)
      }
    })
    res.json({
      data:candidato,
      message:"candidato obtenido correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message:"Error al obtener el candidato",
      error:message.error
    })
  }
})

app.get("/candidatos/:idVotacion", async (req, res) => {
  try {
    let candidato = await prisma.candidato.findMany({
      where: {
        idVotacion: +req.params.idVotacion
      }
    });
    res.json({
      data: candidato,
      message: "candidato obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message:"Error al obtener el candidato",
      error:message.error
    })
  }
})

export default app;