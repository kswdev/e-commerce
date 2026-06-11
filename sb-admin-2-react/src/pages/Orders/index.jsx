import React, { Component } from 'react';

//Navigation
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import PageHeading from "../../components/PageHeading";
import DataTable from "../../components/DataTable/DataTable.tsx";
import { orderColumns } from "../../components/DataTable/columns.tsx";
import { orders } from "../../data/orders.ts";

export default class Orders extends Component {

    componentWillMount() {
        document.getElementById('body').className = 'page-top'
    }

    render() {
        return (
            <div>
                <div id="wrapper">

                    {/* <!-- Sidebar --> */}
                    <Sidebar />
                    <div id="content-wrapper" className="d-flex flex-column">

                        {/* <!-- Main Content --> */}
                        <div id="content">

                            {/* <!-- Topbar --> */}
                            <Topbar />
                            {/* <!-- End of Topbar --> */}

                            {/* <!-- Begin Page Content --> */}
                            <div className="container-fluid">

                                {/* <!-- Page Heading --> */}
                                <PageHeading title="주문 관리" />

                                {/* <!-- DataTable --> */}
                                <div className="card shadow mb-4">
                                    <div className="card-header py-3">
                                        <h6 className="m-0 font-weight-bold text-primary">주문 목록</h6>
                                    </div>
                                    <div className="card-body">
                                        <DataTable data={orders} columns={orderColumns} />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
