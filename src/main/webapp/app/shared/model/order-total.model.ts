export interface IOrderTotal {
  id?: number;
  value?: number;
  orderIdId?: number;
}

export class OrderTotal implements IOrderTotal {
  constructor(public id?: number, public value?: number, public orderIdId?: number) {}
}
