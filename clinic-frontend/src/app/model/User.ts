export class User {
  id: number;
  userName: String;
  name: String;
  surname: String;
  birthDate: Date;

  constructor(id: number, userName: String, name: String, surname: String, birthDate: Date) {
    this.id = id;
    this.userName = userName;
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
  }
}
