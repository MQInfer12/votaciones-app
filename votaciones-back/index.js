import express from "express";
import dotenv from "dotenv";
import bodyParser from "body-parser";
import cors from 'cors';
import votacion from "./controllers/votacion.js";
import candidato from "./controllers/candidato.js";
import asignacion from "./controllers/asignacion.js";

const app = express();
const port = 3000;

dotenv.config();
app.use(cors());
app.use(
  bodyParser.urlencoded({
    extended: false,
  })
);
app.use(bodyParser.json());

app.use(votacion);
app.use(candidato);
app.use(asignacion);

app.listen(port, () => {
  console.log(`⚡️[server]: Server is running at http://localhost:${port}`);
});
