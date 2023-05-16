import {Pipe, PipeTransform} from '@angular/core';
import {Role} from "../model/Role";

@Pipe({
  name: 'roleName'
})
export class RoleNamePipe implements PipeTransform {

  transform(roles: Role[], ...args: unknown[]): unknown {
    return roles?.map(role => role.name);
  }
}
