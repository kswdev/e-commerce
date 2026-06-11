import React, {Component} from 'react';
import {Link} from 'react-router-dom';

import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {clickMenuOpen} from '../../../redux/actions';

class Sidebar extends Component {
    // componentDidMount() {
    //   document.getElementById('body').className = 'page-top';
    // }
    // state = {
    //   sidebarToggled: false,
    // }

    // handleSideBarToggle() {
    //   if (this.sidebarToogled === true) {
    //     this.setState({ sidebarToggled: !this.state.sidebarToggled });
    //     document.getElementById('body').className = 'page-top sidebar-toggled';
    //   } else {
    //     this.setState({ sidebarToggled: !this.state.sidebarToggled });
    //     document.getElementById('body').className = 'page-top';
    //   }

    // }

    render() {
        const {clickMenuOpen, toggled} = this.props;
        return (
            <ul className={toggled ? 'navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled' : 'navbar-nav bg-gradient-primary sidebar sidebar-dark accordion'}
                id="accordionSidebar">

                {/* <!-- Sidebar - Brand --> */}
                <a className="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div className="sidebar-brand-icon rotate-n-15">
                        <i className="fas fa-laugh-wink"></i>F
                    </div>
                    <div className="sidebar-brand-text mx-3">이커머스 ADMIN</div>
                </a>

                {/* <!-- Divider --> */}
                <hr className="sidebar-divider my-0"/>

                {/* <!-- Nav Item - Dashboard --> */}
                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard">
                        <i className="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </Link>
                </li>

                {/* <!-- Divider --> */}
                <hr className="sidebar-divider"/>

                <li className="nav-item">
                    <Link className="nav-link" to="/products">
                        <i className="fas fa-fw fa-chart-area"></i>
                        <span>상품관리</span>
                    </Link>
                </li>

                <hr className="sidebar-divider"/>

                <li className="nav-item">
                    <Link className="nav-link" to="/orders">
                        <i className="fas fa-fw fa-shopping-cart"></i>
                        <span>주문관리</span>
                    </Link>
                </li>

                <hr className="sidebar-divider"/>

                <li className="nav-item">
                    <Link className="nav-link" to="/dashboard">
                        <i className="fas fa-fw fa-user"></i>
                        <span>고객관리</span>
                    </Link>
                </li>

                {/* <!-- Divider --> */}
                <hr className="sidebar-divider d-none d-md-block"/>

                {/* <!-- Sidebar Toggler (Sidebar) --> */}
                <div className="text-center d-none d-md-inline">
                    <button onClick={() => {
                        clickMenuOpen()
                    }} className="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>)
    }
}

const mapDispatchToProps = dispatch =>
    bindActionCreators({clickMenuOpen}, dispatch);

const mapStateToProps = store => ({
    toggled: store.menuState.menuOpen
});

export default connect(mapStateToProps, mapDispatchToProps)(Sidebar);