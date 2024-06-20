import debug from "debug"
import {NextFunction, Request, Response} from "express";
export interface ErrorDetails extends Error{
    statusCode: number;
}
export const requestLogInterceptor = (req: Request, res: Response, next: NextFunction) => {
    const logger = debug('app:logs');
    Object.keys(req.body).length !== 0 && console.log(`{request: ${JSON.stringify(req.body)}}`);
    console.log("USER ID ::: "+JSON.stringify(req.params));
    Object.keys(req.params).length !== 0 && console.log(`{requestPathVariables: ${JSON.stringify(req.params)}}`);
    Object.keys(req.query).length !== 0 && console.log(`{requestQueryVariables: ${JSON.stringify(req.query)}}`);
    res.on('finish', () => {
        logger(`${req.method} ${req.originalUrl} - ${res.statusCode}`);
    });
    next();
}

export const responseLogInterceptor = (err: ErrorDetails, req: Request, res: Response, next: NextFunction) => {
    let resp = {};
    err ? resp = err : res.json();
    let statusCode = err["statusCode"] === undefined ? 200 : err["statusCode"];
    res.status(statusCode).json(resp);
};

export const authenticate = async (req: Request, res: Response, next: NextFunction)=>{
    try{

    }catch (e){
        next(e)
    }
}
