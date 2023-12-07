import Express from "express";
import { PrismaClient } from "@prisma/client";

const app = Express();
const prisma = new PrismaClient();

app.get("/asignacion", async (req, res) => {
  try {
    const asignacion = await prisma.asignacion.findMany();
    res.json({
      data: asignacion,
      message: "asignacion obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener asignacion",
      error: error.message
    })
  }
});


app.post("/asignacion", async (req, res) => {
  try {
    const asignacion = await prisma.asignacion.create({
      data: req.body
    })
    res.json({
      data: asignacion,
      message: "asignacion agregado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al crear asignacion",
      error: error.message
    })
  }
})

app.put("/asignacion/:id", async (req, res) => {
  try {
    const asignacion = await prisma.asignacion.update({
      where: {
        id: Number(req.params.id)
      },
      data: req.body
    })
    res.json({
      data: asignacion,
      message: "asignacion actualizado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al actualizar asignacion",
      error: error.message
    })
  }
})

app.delete("/asignacion/:id", async (req, res) => {
  try {
    const asignacion = await prisma.asignacion.delete({
      where: {
        id: Number(req.params.id)
      }
    })
    res.json({
      data: asignacion,
      message: "asignacion eliminado"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al eliminar asignacion",
      error: error.message
    })
  }
})
app.post("/asignacion/:id",async(req,res)=>{
  try {
    const asignacion=await prisma.asignacion.findMany({
      where:{
        id:Number(req.params.id)
      }
    })
    res.json({
      data:asignacion,
      message:"asignacion obtenido correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message:"Error al obtener el asignacion",
      error:message.error
    })
  }
})

app.get("/mispendientes/:ci", async (req, res)=>{
  try {
    let asignacion = await prisma.asignacion.findMany({
      where: {
        ci: +req.params.ci,
        idCandidato: null 
      },
      include: {
        Votacion: true
      }
    });
    res.json({
      data: asignacion,
      message: "asignacion obtenido con correctamente",
    });
  } catch (error) {
    res.status(500).json({
      message: "Error al obtener asignacion",
      error: error.message
    })
  }
})

app.put("/votar/:id", async (req, res) => {
  try {
    const asignacion = await prisma.asignacion.update({
      where: {
        id: +req.params.id
      },
      data: {
        idCandidato: req.body.idCandidato
      }
    })
    res.json({
      data: asignacion,
      message: "votado correctamente"
    })
  } catch (error) {
    res.status(500).json({
      message: "Error al actualizar asignacion",
      error: error.message
    })
  }
})

export default app;