import React, { Component } from 'react';

interface Props {
  title: string;
  value: string | number;
  icon: string;
  color: string;
}

interface State {
  cardClass: string;
  icon: string;
  titleClass: string;
}

class CardInfo extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      cardClass: '',
      icon: 'fas fa-calendar fa-2x text-gray-300',
      titleClass: '',
    };
  }

  componentDidMount() {
    this.setState({
      cardClass: `card border-left-${this.props.color} shadow h-100 py-2`,
      icon: `fas fa-${this.props.icon} fa-2x text-gray-300`,
      titleClass: `text-xs font-weight-bold text-${this.props.color} text-uppercase mb-1`,
    });
  }

  render() {
    return (
      <div className="col-xl-3 col-md-6 mb-4">
        <div className={this.state.cardClass}>
          <div className="card-body">
            <div className="row no-gutters align-items-center">
              <div className="col mr-2">
                <div className={this.state.titleClass}>{this.props.title}</div>
                <div className="h5 mb-0 font-weight-bold text-gray-800">{this.props.value}</div>
              </div>
              <div className="col-auto">
                <i className={this.state.icon}></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CardInfo;
