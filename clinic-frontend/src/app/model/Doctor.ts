import {AgeGroup} from "./AgeGroup";
import {Rank} from "./Rank";
import {User} from "./User";
import {Specialization} from "./Specialization";
import {Consultation} from "./Consultation";

export class Doctor {
  id: number;
  ageGroup: AgeGroup;
  workExperience: number;
  rank: Rank;
  user: User;
  specializations: Specialization[];
  consultations: Consultation[];

  constructor(id: number, ageGroup: AgeGroup, workExperience: number, rank: Rank, user: User,
              specializations: Specialization[], consultations: Consultation[]) {
    this.id = id;
    this.ageGroup = ageGroup;
    this.workExperience = workExperience;
    this.rank = rank;
    this.user = user;
    this.specializations = specializations;
    this.consultations = consultations;
  }
}
