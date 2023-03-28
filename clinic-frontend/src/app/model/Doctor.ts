import {AgeGroup} from "./AgeGroup";
import {Rank} from "./Rank";
import {User} from "./User";
import {DoctorSpecialization} from "./DoctorSpecialization";

export class Doctor {
  id: Number;
  ageGroup: AgeGroup;
  workExperience: Number;
  rank: Rank;
  user: User;


  constructor(id: Number, ageGroup: AgeGroup, workExperience: Number, rank: Rank, user: User) {
    this.id = id;
    this.ageGroup = ageGroup;
    this.workExperience = workExperience;
    this.rank = rank;
    this.user = user;
  }
}
