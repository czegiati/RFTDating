import {Orientation} from "./orientation";
import {Sex} from "./sex";

export class User {
  username: string;
  email: string;
  fullname: string;
  birthdate: Date;
  sex: Sex;
  orientation: Orientation;
  bio: string;
}
