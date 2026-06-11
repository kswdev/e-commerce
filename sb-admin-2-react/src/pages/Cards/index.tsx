import React, { Component } from 'react';
import CardInfo from '../../components/Cards/Info';
import CardDefault from '../../components/Cards/Default';
import CardBasic from '../../components/Cards/Basic';
import CardCollapse from '../../components/Cards/Collapse';
import CardDropdown from '../../components/Cards/Dropdown';
import PageHeading from '../../components/PageHeading';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';

class Cards extends Component {
  render() {
    return (
      <div>
        <div id="wrapper">
          <Sidebar />
          <div id="content-wrapper" className="d-flex flex-column">
            <div id="content">
              <Topbar />
              <div className="container-fluid">
                <PageHeading title="Cards" />
                <div className="row">
                  <CardInfo title="Earnings (Monthly)" icon="calendar"  color="primary" value="$40,000" />
                  <CardInfo title="Earnings (Annual)"  icon="calendar"  color="success" value="215,000" />
                  <CardInfo title="Tasks"              icon="clipboard" color="info"    value="50%"     />
                  <CardInfo title="Pending Requests"   icon="comments"  color="warning" value="18"      />
                </div>
                <div className="row">
                  <div className="col-lg-6">
                    <CardDefault title="Card Default Example">
                      This card uses Bootstrap's default styling with no utility classes added.
                    </CardDefault>
                  </div>
                  <div className="col-lg-6">
                    <CardDropdown title="Card Dropdown Example">
                      Dropdown menus can be placed in the card header to extend the functionality of a basic card.
                    </CardDropdown>
                  </div>
                </div>
                <div className="row">
                  <div className="col-lg-6">
                    <CardBasic title="Card Basic Example">
                      The styling for this basic card example is created by using default Bootstrap utility classes.
                    </CardBasic>
                  </div>
                  <div className="col-lg-6">
                    <CardCollapse title="Card Collapse Example">
                      This is a collapsable card example. <strong>Click on the card header</strong> to see the card body collapse and expand!
                    </CardCollapse>
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

export default Cards;
