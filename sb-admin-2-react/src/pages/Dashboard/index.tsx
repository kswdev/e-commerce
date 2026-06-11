import React, { Component } from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import CardInfo from '../../components/Cards/Info';
import ChartDonut from '../../components/Charts/Donut';
import ChartLine from '../../components/Charts/Line';
import PageHeading from '../../components/PageHeading';

class Dashboard extends Component {
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
                <PageHeading title="Dashboard" />
                <div className="row">
                  <CardInfo title="Earnings (Monthly)" icon="calendar" color="primary" value="$40,000" />
                  <CardInfo title="Earnings (Annual)"  icon="calendar" color="success" value="215,000" />
                  <CardInfo title="Tasks"              icon="clipboard" color="info"    value="50%"     />
                  <CardInfo title="Pending Requests"   icon="comments"  color="warning" value="18"      />
                </div>
                <div className="row">
                  <div className="col-xl-8 col-lg-6">
                    <ChartLine />
                  </div>
                  <div className="col-xl-4 col-lg-6">
                    <ChartDonut />
                  </div>
                </div>
              </div>
            </div>
            <footer className="sticky-footer bg-white">
              <div className="container my-auto">
                <div className="copyright text-center my-auto">
                  <span>Copyright &copy; Your Website 2019</span>
                </div>
              </div>
            </footer>
          </div>
        </div>
        <a className="scroll-to-top rounded" href="#page-top">
          <i className="fas fa-angle-up"></i>
        </a>
      </div>
    );
  }
}

export default Dashboard;
