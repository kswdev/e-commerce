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
