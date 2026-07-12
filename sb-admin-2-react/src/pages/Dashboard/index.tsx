import React, {useEffect, useState} from 'react';
import Sidebar from '../../components/Navigation/Sidebar';
import Topbar from '../../components/Navigation/Topbar';
import CardInfo from '../../components/Cards/Info';
import ChartDonut from '../../components/Charts/Donut';
import ChartLine from '../../components/Charts/Line';
import PageHeading from '../../components/PageHeading';
import {getDailyCustomerJoinCnt, getDailyCustomerQuitCnt} from "../../api/users";
import {getDailyCancelCnt, getDailyPaymentCnt} from "../../api/payments";

const Dashboard = () => {

    const [dailyCustomerJoinCnt, setDailyCustomerJoinCnt] = useState<number>(0);
    const [dailyCustomerQuitCnt, setDailyCustomerQuitCnt] = useState<number>(0);
    const [dailyPaymentCnt, setDailyPaymentCnt] = useState<number>(0);
    const [dailyCancelCnt, setDailyCancelCnt] = useState<number>(0);
    
    useEffect(() => {
        document.getElementById('body')!.className = 'page-top';
    });

    useEffect(() => {
        getDailyCustomerJoinCnt()
            .then(customerJoinCnt => setDailyCustomerJoinCnt(customerJoinCnt));

        getDailyCustomerQuitCnt()
            .then(customerQuitCnt => setDailyCustomerQuitCnt(customerQuitCnt));

        getDailyPaymentCnt()
            .then(paymentCnt => setDailyPaymentCnt(paymentCnt));

        getDailyCancelCnt()
            .then(cancelCnt => setDailyCancelCnt(cancelCnt));
    }, []);
    
    
    return (
        <div>
            <div id="wrapper">
                <Sidebar/>
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <Topbar/>
                        <div className="container-fluid">
                            <PageHeading title="Dashboard"/>
                            <div className="row">
                                {/* 일별 결제 갯수 */}
                                <CardInfo title="payments (daily)" icon="calendar" color="primary"
                                          value={dailyPaymentCnt}/>
                                {/* 일별 취소 갯수 */}
                                <CardInfo title="cancels (daily)" icon="calendar" color="warning"
                                          value={dailyCancelCnt}/>

                                {/* 일별 가입 고객수 */}
                                <CardInfo title="Joins (daily)" icon="clipboard" color="info"
                                          value={dailyCustomerJoinCnt}/>

                                {/* 일별 탈퇴 갯수 */}
                                <CardInfo title="Quits (daily)" icon="comments" color="warning"
                                          value={dailyCustomerQuitCnt}/>
                            </div>
                            <div className="row">
                                <div className="col-xl-8 col-lg-6">
                                    <ChartLine/>
                                </div>
                                <div className="col-xl-4 col-lg-6">
                                    <ChartDonut/>
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

export default Dashboard;
