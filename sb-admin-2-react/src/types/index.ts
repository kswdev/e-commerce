export interface User {
  id: number;
  name: string;
  email: string;
  role: string;
  status: "active" | "inactive" | "pending";
  joinDate: string;
  score: number;
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
