import React, { Component } from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import PageHeading from '../../components/PageHeading';

class Charts extends Component {
  render() {
    return (
      <div>
        <div id="wrapper">
          <Sidebar />
          <div id="content-wrapper" className="d-flex flex-column">
            <div id="content">
              <Topbar />
              <div className="container-fluid">
                <PageHeading title="Charts" />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Charts;
