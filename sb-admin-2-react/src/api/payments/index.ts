import axiosInstance from '../axiosInstance';

export const getDailyPaymentCnt = () =>
    axiosInstance
        .get<number>('/payments/daily-cnt')
        .then((res) => res.data);

export const getDailyCancelCnt = () =>
    axiosInstance
        .get<number>('/payments/daily-cancel-cnt')
        .then((res) => res.data);