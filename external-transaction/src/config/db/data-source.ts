import "reflect-metadata"
import { DataSource } from "typeorm"
import { User } from "../../entity/user.entity"
import dotenv from "dotenv";
dotenv.config();

export const AppDataSource = new DataSource({
    type: "mysql",
    port: parseInt((process.env.DB_PORT || "4000")),
    host: process.env.DB_HOST,
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    insecureAuth: false,
    connectorPackage: "mysql2",
    synchronize: true,
    // logging: true,
    entities: [User],
    migrations: [],
    subscribers: [],
});
