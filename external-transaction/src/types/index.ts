import * as express from "express";

export interface LoginRequest extends express.Request{
    email: string;
    password: string;
  path: string;
}

export interface UserProfile {
    id: string;
    username: string;
    email: string;
}

export interface ISignupRequest extends express.Request {
    email: any;
    name: string;
    password: string;
  path: string;
}

export interface IUserIdRequest extends express.Request {
    userId: number
}

export interface IUpdateProfileRequest extends express.Request {
    userId: number,
    username: any,
    firstName: string,
    lastName: string,
    phoneNumber: string,
    address: string,
}
