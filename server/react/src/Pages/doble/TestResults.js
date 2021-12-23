import React, { useState, useEffect, useMemo } from "react";
import Axios from "axios";
import { Table } from "antd";
import { Button, Col, Select, Row } from "antd";
import { Modal } from 'antd';
import { Descriptions } from 'antd';


function TestResults({ transformerName }) {
  const [transformerData, setTransformerData] = useState({})
  const [selectedDtaxFile, setSelectedDtaxFile] = useState('')
  const [selectedSessionOpt, setSelectedSessionOpt] = useState(0)
  const [loading, setloading] = useState(true);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [bushingPlate, setBushingPlate] = useState({})


  const dtaxFileData = useMemo(() => {
    setSelectedSessionOpt(0)
    return transformerData[selectedDtaxFile] || {}
  }, [transformerData, selectedDtaxFile])

  const transformerDetails = useMemo(() => {
    return dtaxFileData?.['transformerDetails'] || {}
  }, [dtaxFileData])

  const trasformerBushingNamePlates = useMemo(() => {
    return dtaxFileData?.transformerDetails?.['trasformerBushingNamePlates']?.['trasformerBushingNamePlate'] || []
  }, [dtaxFileData])

  const sessionOptions = useMemo(() => {
    let _data = dtaxFileData?.['transformerSessions']
    return _data ? Object.keys(_data) : []
  }, [dtaxFileData])

  const sessionData = useMemo(() => {
    return dtaxFileData?.['transformerSessions']?.[selectedSessionOpt] || []
  }, [dtaxFileData, selectedSessionOpt])

  const excitingTestResults = useMemo(() => {
    return sessionData?.['excitingTestResults'] || []
  }, [sessionData])

  const overallTest = useMemo(() => {
    return sessionData?.['overallTest'] || []
  }, [sessionData])



  useEffect(() => {
    getData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [transformerName]);

  const getData = () => {

    Axios.post("http://3.108.191.191:1048/api/datamodel", {
      id: transformerName
    }).then((res) => {
      setloading(false);
      setTransformerData(res.data)
      setSelectedDtaxFile(Object.keys(res.data)[0])
    });
  };

  const overallcolumns = [
    {
      title: "Line-id",
      dataIndex: "lineId",
      // width: 150,
    },
    {
      title: "Insulation System",
      dataIndex: "insulation",
      // width: 150,
    },
    {
      title: "Test kV",
      dataIndex: "requestedTestKV",
      // width: 15,
    },
    {
      title: "V (kV)",
      dataIndex: "testKV",
      // width: 150,
      render: (testkV) => (testkV ? parseFloat(testkV).toFixed(3) : testkV),
    },
    {
      title: "I (mA)",
      dataIndex: "ma",
      // width: 150,
      render: (mA) => (mA ? parseFloat(mA).toFixed(3) : mA),
    },
    {
      title: "Loss (W)",
      dataIndex: "watts",
      // width: 150,
      render: (watts) => (watts ? parseFloat(watts).toFixed(3) : watts),
    },
    {
      title: "TCF (#)",
      dataIndex: "correctionFactor",
      // width: 150,
      render: (correctionFactor) =>
        correctionFactor
          ? parseFloat(correctionFactor).toFixed(3)
          : correctionFactor,
    },
    {
      title: "PF (%)",
      dataIndex: "pfm",
      // width: 150,
      render: (pfm) => (pfm ? parseFloat(pfm).toFixed(3) : pfm),
    },
    {
      title: "PF*TCF (%)",
      dataIndex: "pfc",
      // width: 150,
      render: (pfc) => (pfc ? parseFloat(pfc).toFixed(3) : pfc),
    },
    {
      title: "Capacitance (pF)",
      dataIndex: "measuredCap",
      width: 120,
      render: (measuredCap) =>
        measuredCap ? parseFloat(measuredCap).toFixed(3) : measuredCap,
    },
    {
      title: "Ask Frank",
      dataIndex: "ratingExpertSystem",
      width: 150,
    },
    {
      title: "Manual",
      dataIndex: "ratingTester",
      // width: 150,
    }
  ];


  const overallcolumns1 = [
    {
      title: "Line-id",
      dataIndex: "lineId",
      // width: 150,
    },
    {
      title: "Insulation System",
      dataIndex: "insulation",
      // width: 150,
    },
    {
      title: "PF (%)",
      dataIndex: "pfm",
      // width: 150,
      render: (pfm) => (pfm ? parseFloat(pfm).toFixed(3) : pfm),
    },
    {
      title: "PF*TCF (%)",
      dataIndex: "pfc",
      // width: 150,
      render: (pfc) => (pfc ? parseFloat(pfc).toFixed(3) : pfc),
    },
    {
      title: "Capacitance (pF)",
      dataIndex: "measuredCap",
      width: 120,
      render: (measuredCap) =>
        measuredCap ? parseFloat(measuredCap).toFixed(3) : measuredCap,
    },

  ];

  const excitationColumns = [
    {
      title: "Include In Plot",
      dataIndex: "includeInPlot",
      width: 130,
    },
    {
      title: "Test kV",
      dataIndex: 'excitingCurrentFields',
      render: (rowData) => {
        let testKV = rowData.testKV
        return (testKV ? parseFloat(testKV).toFixed(3) : testKV)
      },
      width: 80,
    },
    {
      title: "PhaseA - V (kV)",
      dataIndex: 'excitingCurrentFields',
      render: (rowData) => {
        let actualkv1 = rowData.actualkv1
        return (actualkv1 ? parseFloat(actualkv1).toFixed(3) : actualkv1)
      },
      width: 135,
    },
    {
      title: "PhaseA - I (mA)",
      dataIndex: 'excitingCurrentFields',
      width: 135,
      render: (rowData) => {
        let ma1 = rowData.ma1
        return (ma1 ? parseFloat(ma1).toFixed(3) : ma1)
      },
    },
    {
      title: "PhaseA - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 140,
      render: (rowData) => {
        let watts1 = rowData.watts1
        return (watts1 ? parseFloat(watts1).toFixed(3) : watts1)
      },
    },
    {
      title: "PhaseA - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC1 = rowData.lOrC1
        return (lOrC1 ? lOrC1 : lOrC1)
      },
    },
    {
      title: "PhaseB - V (kV)",
      dataIndex: 'excitingCurrentFields',
      render: (rowData) => {
        let actualkv2 = rowData.actualkv2
        return (actualkv2 ? parseFloat(actualkv2).toFixed(3) : actualkv2)
      },
      width: 135,
    },
    {
      title: "PhaseB - I (mA)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let ma2 = rowData.ma2
        return (ma2 ? parseFloat(ma2).toFixed(3) : ma2)
      },
    },
    {
      title: "PhaseB - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let watts2 = rowData.watts2
        return (watts2 ? parseFloat(watts2).toFixed(3) : watts2)
      },
    },
    {
      title: "PhaseB - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC2 = rowData.lOrC2
        return (lOrC2 ? lOrC2 : lOrC2)
      },
    },
    {
      title: "PhaseC - V (kV)",
      dataIndex: 'excitingCurrentFields',
      render: (rowData) => {
        let actualkv3 = rowData.actualkv3
        return (actualkv3 ? parseFloat(actualkv3).toFixed(3) : actualkv3)
      },
      width: 135,
    },
    {
      title: "PhaseC - I (mA)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let ma3 = rowData.ma3
        return (ma3 ? parseFloat(ma3).toFixed(3) : ma3)
      },
    },
    {
      title: "PhaseC - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let watts3 = rowData.watts3
        return (watts3 ? parseFloat(watts3).toFixed(3) : watts3)
      },
    },
    {
      title: "PhaseC - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC3 = rowData.lOrC3
        return (lOrC3 ? lOrC3 : lOrC3)
      },
    },
    {
      title: "Ask Frank",
      dataIndex: 'excitingCurrentFields',
      render: (rowData) => {
        let ratingExpertSystem = rowData.ratingExpertSystem
        return (ratingExpertSystem ? ratingExpertSystem : ratingExpertSystem)
      },
    },
    {
      title: "Manual",
      dataIndex: "excitingCurrentFields",
      render: (rowData) => {
        let ratingTester = rowData.ratingTester
        return (ratingTester ? ratingTester : ratingTester)
      },
    },
  ];


  const excitationColumns1 = [
    {
      title: "Include In Plot",
      dataIndex: "includeInPlot",
      width: 130,
    },
    {
      title: "PhaseA - I (mA)",
      dataIndex: 'excitingCurrentFields',
      width: 135,
      render: (rowData) => {
        let ma1 = rowData.ma1
        return (ma1 ? parseFloat(ma1).toFixed(3) : ma1)
      },
    },
    {
      title: "PhaseA - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 140,
      render: (rowData) => {
        let watts1 = rowData.watts1
        return (watts1 ? parseFloat(watts1).toFixed(3) : watts1)
      },
    },
    {
      title: "PhaseA - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC1 = rowData.lOrC1
        return (lOrC1 ? lOrC1 : lOrC1)
      },
    },
    {
      title: "PhaseB - I (mA)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let ma2 = rowData.ma2
        return (ma2 ? parseFloat(ma2).toFixed(3) : ma2)
      },
    },
    {
      title: "PhaseB - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let watts2 = rowData.watts2
        return (watts2 ? parseFloat(watts2).toFixed(3) : watts2)
      },
    },
    {
      title: "PhaseB - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC2 = rowData.lOrC2
        return (lOrC2 ? lOrC2 : lOrC2)
      },
    },
    {
      title: "PhaseC - I (mA)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let ma3 = rowData.ma3
        return (ma3 ? parseFloat(ma3).toFixed(3) : ma3)
      },
    },
    {
      title: "PhaseC - Loss (w)",
      dataIndex: "excitingCurrentFields",
      width: 135,
      render: (rowData) => {
        let watts3 = rowData.watts3
        return (watts3 ? parseFloat(watts3).toFixed(3) : watts3)
      },
    },
    {
      title: "PhaseC - L/C",
      dataIndex: "excitingCurrentFields",
      width: 100,
      render: (rowData) => {
        let lOrC3 = rowData.lOrC3
        return (lOrC3 ? lOrC3 : lOrC3)
      },
    },
  ];

  const bushingColumns = [
    {
      title: "Name",
      dataIndex: "catalogNum",
    },
    {
      title: "Manufacurer",
      dataIndex: "mfr",
    },
    {
      title: "Bushing Serial Number",
      dataIndex: "bushingSerialNum",
    },
    {
      title: "BIL",
      dataIndex: "bil",
    },
    {
      title: "Cap1 (PF)",
      dataIndex: "c1Cap",
    },
    {
      title: "PF (%)",
      dataIndex: "c1Pf",
    },
    {
      title: "Cap2 (PF)",
      dataIndex: "c2Cap",
    },
    {
      title: "PF (%)",
      dataIndex: "c2Pf",
    },
  ]

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <React.Fragment>
      <h3 className="page-header">Doble Test Results</h3>
      <div className="page-header-container">
        <h3>Transformer Details</h3>
        <Select defaultValue={transformerName} onChange={(e) => setSelectedDtaxFile(e)}>
          {
            Object.keys(transformerData).map((item, index) => <Select.Option key={index} value={item}>{item}</Select.Option>)
          }
        </Select>
      </div>
      <div className="box-container">
        <Descriptions  >
          <Descriptions.Item label="Serial No">{transformerDetails.serialnum}</Descriptions.Item>
          <Descriptions.Item label="Manufacturer">{transformerDetails.mfr}</Descriptions.Item>
          <Descriptions.Item label="Mfg Year">{transformerDetails.yearmfg}</Descriptions.Item>
          <Descriptions.Item label="Apparatus Type">{transformerDetails.apparatustype}</Descriptions.Item>
          <Descriptions.Item label="Tank Type">{transformerDetails.tanktype}</Descriptions.Item>
          <Descriptions.Item label="Coolant">{transformerDetails.coolant}</Descriptions.Item>
          <Descriptions.Item label="Config">{transformerDetails.config}</Descriptions.Item>
          <Descriptions.Item label="KV-2">{transformerDetails.kV2}</Descriptions.Item>
          <Descriptions.Item label="KV-1">{transformerDetails.kV1}</Descriptions.Item>
          <Descriptions.Item label="KV-0">{transformerDetails.kV0}</Descriptions.Item>
          <Descriptions.Item label="Class">{transformerDetails.clas}</Descriptions.Item>
          <Descriptions.Item label="Oil Volume">{transformerDetails.oilvolume}</Descriptions.Item>
          <Descriptions.Item label="Weight">{transformerDetails.weight}</Descriptions.Item>
          <Descriptions.Item label="BIL">{transformerDetails.bil}</Descriptions.Item>
          <Descriptions.Item label="Phase">{transformerDetails.phases}</Descriptions.Item>
        </Descriptions>
      </div>

      <div className="page-header-container">
        <h3>Overall Test Data</h3>
        <Col>
          <Button
            type="primary"
            style={{ marginRight: "10px" }}
          >
            Import All
          </Button>
          <Select value={selectedSessionOpt} placeholder="Session 0" default={0} style={{ width: 120 }} onChange={(val) => setSelectedSessionOpt(val)}>
            {
              sessionOptions?.length && sessionOptions?.map((_, idx) => <Select.Option key={idx} value={idx}>Session {idx}</Select.Option>)
            }
          </Select>
          <Button
            type="primary"
            style={{ marginLeft: "10px" }}
            onClick={showModal}
          >
            Preview
          </Button>
          <Modal title="OverAll Impport Data" visible={isModalVisible} onOk={handleOk} cancelText="Import" cancelButtonProps={() => handleOk()} centered width={800}>
            <Table
              columns={overallcolumns1}
              dataSource={overallTest}
              pagination={true}
              // scroll={{ y: 400 }}
            />
          </Modal>
        </Col>
      </div>

      <div className="box-container">
      <Table
        columns={overallcolumns}
        dataSource={overallTest}
        pagination={true}
        // scroll={{ y: 300 }}
        loading={loading}
      />
      </div>
      <div className="page-header-container">
        <h3>Excitation Test Data</h3>
        <Button
          type="primary"
          style={{ marginLeft: "10px"}}
        >
          Preview
        </Button>
        {/* <Modal title="Excitation Import Data" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel} width={1000}>
             <Table
              columns={excitationColumns1}
              dataSource={excitationState}
              pagination={false}
            />
        </Modal> */}
      </div>
      <div className="box-container">
      <Table
        columns={excitationColumns}
        dataSource={excitingTestResults}
        // pagination={true}
        // scroll={{ y: 300, x:500 }} 
        loading={loading}
      />
      </div>

      <div className="page-header-container">
        <h3>Bushing Test Data</h3>
        <Button
          type="primary"
          style={{ marginLeft: "10px"}}
        >
          Preview
        </Button>
      </div>
      <div className="box-container">
      <Table
        columns={bushingColumns}
        dataSource={trasformerBushingNamePlates}
        // pagination={true}
        scroll={{ y: 300, x:500 }} 
        loading={loading}
      />
      </div>
    </React.Fragment>
  );
}

export default TestResults;



// 3.108.191.191
