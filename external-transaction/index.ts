import app from "./src/app";
import {AppDataSource} from "./src/config/db/data-source";
import * as dotenv from "dotenv";
dotenv.config();
const port = process.env.PORT || 4000;


app.listen(port, () => {
    console.log(`[server]: Server is running at http://localhost:${port}/api/v1`);

    AppDataSource.initialize().then(async () => {
        console.log("Database Initialized...");
    }).catch(error => {
        console.log("Error occurred while Initializing database..."+error);
    })
});
