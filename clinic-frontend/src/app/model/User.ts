export class User {
  id: Number;
  userName: String;
  password: String;
  name: String;
  surname: String;
  birthDate: Date;

  constructor(id: Number, userName: String, password: String, name: String, surname: String, birthDate: Date) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }
}
