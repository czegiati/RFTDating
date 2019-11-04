import { Sex } from './sex';
import { Orientation } from './orientation';

export interface RegisterCredentials {
  username: string;
  fullname: string;
  birthdate: Date;
  sex: Sex;
  orientation: Orientation;
  email: string;
  password: string
}
