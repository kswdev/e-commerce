import axiosInstance from '../axiosInstance';
import {PageResponse, Product} from '../../types';

export const getProducts = (page: number, size: number) =>
    axiosInstance
        .get<PageResponse<Product>>('/products', { params: { page, size } })
        .then((res) => res.data);
