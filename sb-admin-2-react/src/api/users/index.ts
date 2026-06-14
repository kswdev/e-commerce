import axiosInstance from '../axiosInstance';
import { Customer, PageResponse } from '../../types';

export const getCustomers = (page: number, size: number) =>
    axiosInstance
        .get<PageResponse<Customer>>('/customers', { params: { page, size } })
        .then((res) => res.data);
