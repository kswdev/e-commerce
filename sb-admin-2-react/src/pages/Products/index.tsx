import React, {useEffect} from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import PageHeading from '../../components/PageHeading';
import DataTable from '../../components/DataTable/DataTable.tsx';
import {productColumns} from '../../components/DataTable/columns.tsx';
import {products} from '../../data/products.ts';
import {useNavigate} from "react-router-dom";

const Products = () => {

    const navigate = useNavigate();

    useEffect(() => {
        document.getElementById('body')!.className = 'page-top';
    })

    return (
        <div>
            <div id="wrapper">
                <Sidebar/>
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <Topbar/>
                        <div className="container-fluid">
                            <PageHeading title="상품 관리"/>
                            <div className="card shadow mb-4">
                                <div className="card-header py-3">
                                    <h6 className="m-0 font-weight-bold text-primary">상품 목록</h6>
                                </div>
                                <div className="card-body">
                                    <DataTable data={products}
                                               columns={productColumns}
                                               onRowClick={(product) => navigate(`/products/${product.id}`)}/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Products;
