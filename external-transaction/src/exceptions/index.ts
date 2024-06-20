export class  NotFoundException extends Error{
    private statusCode: number;
    private path: string;
  private error: string | undefined;
    constructor(message: string, path: string) {
        super();
        this.message = message;
        this.statusCode = 404;
        this.path = path
        this.error = (new Error()).stack
    }
}


export class  AlreadyExistException extends Error{
    private statusCode: number;
    private path: string;
  private error: string | undefined;
    constructor(message: string, path: string) {
        super();
        this.message = message;
        this.statusCode = 409;
        this.path = path
        this.error = (new Error()).stack
    }
}


export class DatabaseError extends Error{
    private statusCode: number;
    private path: string;
  private error: string | undefined;
    constructor(message: string, path: string) {
        super();
        this.message = message;
        this.statusCode = 400;
        this.path = path
        this.error = (new Error()).stack
    }
}

export class  UnauthorizedException extends Error{
   private statusCode: number;
   private path: string;
   private error: string | undefined;
   constructor(message: string, path: string) {
      super();
      this.message = message;
      this.statusCode = 401;
      this.path = path
      this.error = (new Error()).stack
   }
}

export class  APIException extends Error{
  private statusCode: number;
  private path: string;
  private error: string | undefined;
  constructor(message: string, path: string) {
    super();
    this.message = message;
    this.statusCode = 401;
    this.path = path
    this.error = (new Error()).stack
  }
}
