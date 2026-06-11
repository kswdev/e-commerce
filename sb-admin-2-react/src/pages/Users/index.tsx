import React, { Component } from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import PageHeading from '../../components/PageHeading';
import DataTable from '../../components/DataTable/DataTable.tsx';
import { userColumns } from '../../components/DataTable/columns.tsx';
import { users } from '../../data/users.ts';

class Users extends Component {
  UNSAFE_componentWillMount() {
    document.getElementById('body')!.className = 'page-top';
  }

  render() {
    return (
      <div>
        <div id="wrapper">
          <Sidebar />
          <div id="content-wrapper" className="d-flex flex-column">
            <div id="content">
              <Topbar />
              <div className="container-fluid">
                <PageHeading title="고객 관리" />
                <div className="card shadow mb-4">
                  <div className="card-header py-3">
                    <h6 className="m-0 font-weight-bold text-primary">고객 목록</h6>
                  </div>
                  <div className="card-body">
                    <DataTable data={users} columns={userColumns} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Users;
