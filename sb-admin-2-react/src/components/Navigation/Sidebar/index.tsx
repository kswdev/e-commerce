import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { bindActionCreators, Dispatch } from 'redux';
import { clickMenuOpen } from '../../../redux/actions';
import { RootState } from '../../../types';

interface StateProps {
  toggled: boolean;
}

interface DispatchProps {
  clickMenuOpen: () => void;
}

type Props = StateProps & DispatchProps;

class Sidebar extends Component<Props> {
  render() {
    const { clickMenuOpen, toggled } = this.props;
    return (
      <ul
        className={
          toggled
            ? 'navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled'
            : 'navbar-nav bg-gradient-primary sidebar sidebar-dark accordion'
        }
        id="accordionSidebar"
      >
        {/* Sidebar - Brand */}
        <a className="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
          <div className="sidebar-brand-icon rotate-n-15">
            <i className="fas fa-laugh-wink"></i>
          </div>
          <div className="sidebar-brand-text mx-3">이커머스 ADMIN</div>
        </a>

        <hr className="sidebar-divider my-0" />

        <li className="nav-item">
          <Link className="nav-link" to="/dashboard">
            <i className="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </Link>
        </li>

        <hr className="sidebar-divider" />

        <li className="nav-item">
          <Link className="nav-link" to="/products">
            <i className="fas fa-fw fa-chart-area"></i>
            <span>상품관리</span>
          </Link>
        </li>

        <hr className="sidebar-divider" />

        <li className="nav-item">
          <Link className="nav-link" to="/orders">
            <i className="fas fa-fw fa-shopping-cart"></i>
            <span>주문관리</span>
          </Link>
        </li>

        <hr className="sidebar-divider" />

        <li className="nav-item">
          <Link className="nav-link" to="/users">
            <i className="fas fa-fw fa-user"></i>
            <span>고객관리</span>
          </Link>
        </li>

        <hr className="sidebar-divider d-none d-md-block" />

        <div className="text-center d-none d-md-inline">
          <button
            onClick={() => clickMenuOpen()}
            className="rounded-circle border-0"
            id="sidebarToggle"
          ></button>
        </div>
      </ul>
    );
  }
}

const mapStateToProps = (store: RootState): StateProps => ({
  toggled: store.menuState.menuOpen,
});

const mapDispatchToProps = (dispatch: Dispatch): DispatchProps =>
  bindActionCreators({ clickMenuOpen }, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(Sidebar);
