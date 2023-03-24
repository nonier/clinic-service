import {User} from "../model/User";
import {Doctor} from "../model/Doctor";
import {Rank} from "../model/Rank";
import {AgeGroup} from "../model/AgeGroup";

export class TestData {

  static users: User[] = [
    {id: 1, userName: 'Admin', password: '1234', name: 'Admin', surname: 'Admin', birthDate: new Date(2023, 7, 4)},
    {id: 2, userName: 'Doctor', password: '4321', name: 'Oleg', surname: 'Olegov', birthDate: new Date(2020, 7, 4)}
  ];

  static doctors: Doctor[] = [
    {id: 1, rank: Rank.EXPERT, user: TestData.users[0], workExperience: 23, ageGroup: AgeGroup.ALL },
    {id: 2, rank: Rank.CONSULTANT, user: TestData.users[1], workExperience: 3, ageGroup: AgeGroup.CHILDREN }
  ];
}
