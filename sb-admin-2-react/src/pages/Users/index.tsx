import React, { useCallback, useEffect, useState } from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import PageHeading from '../../components/PageHeading';
import DataTable from '../../components/DataTable/DataTable.tsx';
import { customerColumns } from '../../components/DataTable/columns.tsx';
import { Customer } from '../../types';
import { getCustomers } from '../../api/users';

const Users = () => {
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [pageIndex, setPageIndex] = useState(0);
    const [pageSize, setPageSize] = useState(5);
    const [totalElements, setTotalElements] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        document.getElementById('body')!.className = 'page-top';
    });

    useEffect(() => {
        setLoading(true);
        getCustomers(pageIndex, pageSize)
            .then((page) => {
                setCustomers(page.content);
                setTotalElements(page.totalElements);
                setTotalPages(page.totalPages);
            })
            .catch((err) => setError(err.message))
            .finally(() => setLoading(false));
    }, [pageIndex, pageSize]);

    const handlePaginationChange = useCallback((page: number, size: number) => {
        setPageIndex(page);
        setPageSize(size);
    }, []);

    return (
        <div>
            <div id="wrapper">
                <Sidebar/>
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <Topbar/>
                        <div className="container-fluid">
                            <PageHeading title="고객 관리"/>
                            <div className="card shadow mb-4">
                                <div className="card-header py-3">
                                    <h6 className="m-0 font-weight-bold text-primary">고객 목록</h6>
                                </div>
                                <div className="card-body">
                                    {loading && <p>로딩 중...</p>}
                                    {error && <p className="text-danger">{error}</p>}
                                    {!loading && !error && (
                                        <DataTable
                                            data={customers}
                                            columns={customerColumns}
                                            serverPagination={{
                                                pageIndex,
                                                pageSize,
                                                pageCount: totalPages,
                                                totalElements,
                                                onPaginationChange: handlePaginationChange,
                                            }}
                                        />
                                    )}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Users;
