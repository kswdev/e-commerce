import axiosInstance from '../axiosInstance';
import { Customer } from '../../types';

export const getCustomers = () =>
    axiosInstance.get<Customer[]>('/customers').then((res) => res.data);
