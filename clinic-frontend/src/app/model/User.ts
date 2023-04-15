export class User {
  id?: number;
  username: string;
  password?: string;
  name?: string;
  surname?: string;
  birthDate?: Date;

  constructor(id: number, username: string, password: string, name: string, surname: string, birthDate: Date) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }
}
