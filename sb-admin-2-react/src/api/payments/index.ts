import axiosInstance from '../axiosInstance';

export const getDailyPaymentCnt = () =>
    axiosInstance
        .get<number>('/payments/cnt')
        .then((res) => res.data);

export const getDailyCancelCnt = () =>
    axiosInstance
        .get<number>('/payments/cancel-cnt')
        .then((res) => res.data);