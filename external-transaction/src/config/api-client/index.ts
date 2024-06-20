import * as dotenv from "dotenv";
import axios, {AxiosRequestConfig} from "axios";

dotenv.config();
const baseUrl = process.env.GATEWAY_BASE_URL;
console.log("THE BASE URL: " + baseUrl);
const credentials = 'include';
const clientId = process.env.GATEWAY_CLIENT_ID || "client";
const clientSecret = process.env.GATEWAY_CLIENT_SECRET || "client";
const webClient = axios.create({
  baseURL: baseUrl,
  headers: {
    'Content-Type': 'application/json',
    // Authorization: `Basic ${Buffer.from(`${clientId}:${clientSecret}`).toString('base64')}`,
  },
  // withCredentials: true,
});

export default webClient;