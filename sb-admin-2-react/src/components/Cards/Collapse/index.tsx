import React, { Component } from 'react';

interface Props {
  title?: string;
  children?: React.ReactNode;
}

interface State {
  title: string;
}

class CardCollapse extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { title: '' };
  }

  componentDidMount() {
    this.setState({ title: this.props.title ?? 'Basic Card Example' });
  }

  render() {
    return (
      <div className="card shadow mb-4">
        <a
          href="#collapseCardExample"
          className="d-block card-header py-3"
          data-toggle="collapse"
          role="button"
          aria-expanded="true"
          aria-controls="collapseCardExample"
        >
          <h6 className="m-0 font-weight-bold text-primary">{this.state.title}</h6>
        </a>
        <div className="collapse show" id="collapseCardExample">
          <div className="card-body">
            {this.props.children}
          </div>
        </div>
      </div>
    );
  }
}

export default CardCollapse;
