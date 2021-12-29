import { Button, Col, Input, Row, Space, Table } from "antd";
import React from "react";
import { SearchOutlined } from "@ant-design/icons";
import { useHistory } from "react-router-dom";
import { useState, useEffect } from "react";
import Axios from "axios";


function Home() {
  const [state, setState] = useState([]);
  const [loading, setloading] = useState(true);
  const [globalFilter, setGlobalFilter] = useState(null);
  const history = useHistory();
  useEffect(() => {
    getData();
  }, []);


  const getData = async () => {
    await Axios.get("http://3.108.191.191:1048/api/homepage/list/").then(
      res => {
        setloading(false);
        setState(res.data.map(row => ({
          'serial_num': row
        })));
      }
    );
  };

  const columns = [
    {title: "Serial No", dataIndex: 'serial_num', align: "left", key: 'serial_num' },
    // { title: "Manufacturer", dataIndex: "mfr", align: "center", key: "mfr"},
    // { title: "Mfg Year", dataIndex: "year-mfg", align: "center", key: "year-mfg"},
    // { title: "Appartus Type", dataIndex: "apparatus-type", align: "center", key: "apparatus-type"},
    // { title: "Tank Type", dataIndex: "tanktype", align: "center", key: "tanktype"},
    // { title: "Coolant", dataIndex: "coolant", align: "center", key: "coolant"},
    // { title: "BIL", dataIndex: "bil", align: "center", key: "bil"},
    // { title: "Weight", dataIndex: "weight", align: "center", key: "weight"},
    // { title: "Phase", dataIndex: "phases", align: "center", key: "phases"},
    // { title: "Va-0", dataIndex: "Va-0", align: "center", key: "Va-0"},
    // { title: "Va-1", dataIndex: "Va-1", align: "center", key: "Va-1"},
    // { title: "Va-2", dataIndex: "Va-2", align: "center", key: "Va-2"},
    {
      title: "Action", align: "left", dataIndex: "action", key: "action",
      render: (text, record) => (
        <Space size="middle">
          <Button>GEN</Button>
          <Button
            onClick={() => {
              history.push({
                pathname: '/test-results',
                state: { transformerName: record['serial_num'] }
              })
            }
            }
          >
            DOBLE
          </Button>
          <Button
            onClick={() =>
              history.push({
                pathname: '/impulse-results',
                state: { serial_num: record.serial_num }
              })
            }
          >
            IMPULSE</Button>
        </Space>
      ),
    },
  ];

  return (
    <React.Fragment>
      <h3 className="page-header">TDMS Dashboard</h3>
      <div className="page-header-container">
        <h3>Transformers List</h3>
        <Row style={{ justifyContent: "flex-start" }}>
            <Col>
              <Input placeholder="Search" onInput={(e) => setGlobalFilter(e.target.value)} prefix={<SearchOutlined />} />
            </Col>
            <Col>
              <Button type="primary">Search</Button>
            </Col>
          </Row>
      </div>
      <div className="box-container">
      <Table 
        dataSource={state}
        columns={columns}
        rowKey="id"
       
        //   loading={loading}
        size="small"
        bordered
        globalFilter={globalFilter}
      />
      </div>
    </React.Fragment>
  );
}

export default Home;