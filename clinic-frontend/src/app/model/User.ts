export class User {
  id: number;
  userName: String;
  password: String;
  name: String;
  surname: String;
  birthDate: Date;

  constructor(id: number, userName: String, password: String, name: String, surname: String, birthDate: Date) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }
}
