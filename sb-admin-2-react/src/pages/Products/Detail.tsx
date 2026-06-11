import React from 'react';
import Sidebar from "../../components/Navigation/Sidebar";
import Topbar from "../../components/Navigation/Topbar";
import PageHeading from "../../components/PageHeading";

const ProductDetail = () => {

    return (
        <div>
            <div id="wrapper">
                <Sidebar />
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <Topbar />
                        <div className="container-fluid">
                            <PageHeading title="제품 상세 페이지" />
                            <div className="card shadow mb-4">
                                <div className="card-header py-3">
                                    <h6 className="m-0 font-weight-bold text-primary">준비중입니다...</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProductDetail;