import {Component, OnInit} from '@angular/core';
import {IMultiSelectOption, IMultiSelectTexts} from "ngx-bootstrap-multiselect";
import {SpecializationService} from "../../service/specialization/specialization.service";
import {Specialization} from "../../model/Specialization";
import {DoctorService} from "../../service/doctor/doctor.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{

  nameFilter: string = "";
  specializationIdsFilter: number[] = [];
  specializationOptions: IMultiSelectOption[] = [];
  specializationsTexts: IMultiSelectTexts = {
    defaultTitle: "Специализации"
  }

  constructor(private doctorService: DoctorService,
              private specializationService: SpecializationService) {
  }

  ngOnInit(): void {
    this.specializationService.specializationSubject
      .subscribe((specializations: Specialization[]) => {
        this.specializationOptions = specializations;
      })
  }

  findByFilter(name: string) {
    this.nameFilter = name;
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }

  onChangeSpecializations() {
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }
}
