export interface Customer {
  customerId: number;
  customerName: string;
  age: number;
  phoneNumber: string;
  address: string;
  grade: "BASIC" | "VIP";
  deleted: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface Product {
  id: number;
  name: string;
  price: number;
  company: string;
  createdAt: string;
  remnant: number;
}

export interface Order {
  id: number;
  orderer: string;
  price: number;
  status: string;
  paymentMethod: string;
  orderDate: number;
}

export interface RootState {
  menuState: {
    menuOpen: boolean;
  };
}
