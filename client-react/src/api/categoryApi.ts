import axiosInstance from './axiosInstance';

export const fetchCategories = async (): Promise<string[]> => {
    const res = await axiosInstance.get<string[]>('/categories');
    return res.data;
};
