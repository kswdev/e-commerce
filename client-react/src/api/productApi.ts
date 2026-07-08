import axiosInstance from './axiosInstance';
import { Product } from '../services/productService';

export const fetchProducts = async (): Promise<Product[]> => {
    const res = await axiosInstance.get<Product[]>('/products');
    return res.data;
};

export const fetchProductById = async (id: number): Promise<Product> => {
    const res = await axiosInstance.get<Product>(`/products/${id}`);
    return res.data;
};
