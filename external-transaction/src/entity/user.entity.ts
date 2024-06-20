import { Entity, PrimaryGeneratedColumn, Column } from "typeorm"

@Entity({ name: "user" })
export class User {

    @PrimaryGeneratedColumn({ type: "int", name: "id" })
    id: number;

    @Column({ type: "varchar", length: 50, nullable: false, name: "username" })
    username: string;

    @Column({ type: "varchar", length: 50, nullable: false, name: "first_name" })
    firstName: string;

    @Column({ type: "varchar", length: 50, nullable: false, name: "last_name" })
    lastName: string;

    @Column({ type: "varchar", length: 50, nullable: true, name: "phone_number" })
    phoneNumber: string;

    @Column({ type: "varchar", nullable: true, name: "address" })
    address: string;
}
