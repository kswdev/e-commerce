import axiosInstance from '../axiosInstance';
import { Customer, PageResponse } from '../../types';

export const getCustomers = (page: number, size: number) =>
    axiosInstance
        .get<PageResponse<Customer>>('/customers', { params: { page, size } })
        .then((res) => res.data);

export const getDailyCustomerJoinCnt = () =>
    axiosInstance
        .get<number>('/customers/daily-join-cnt')
        .then((res) => res.data);

export const getDailyCustomerQuitCnt = () =>
    axiosInstance
        .get<number>('/customers/daily-quit-cnt')
        .then((res) => res.data);