
function chart01(id) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
            {
                name: '注册类型分析',
                type: 'pie',
                radius: ['45%','58%'],
                center: ['44%', '53%'],
                avoidLabelOverlap: true,
                hoverOffset: 8,
                data: [
                    {
                        value: 35,
                        name: '国家机关法人',
                        itemStyle: {
                            color: "#3dbea9"
                        }
                    },
                    {
                        value: 25,
                        name: '事业单位法人',
                        itemStyle: {
                            color: "#e68a5e"
                        }
                    },
                    {
                        value: 15,
                        name: '社会团体法人',
                        itemStyle: {
                            color: "#dcb529"
                        }
                    },
                    {
                        value: 175,
                        name: '企业法人',
                        itemStyle: {
                            color: "#2586e9"
                        }
                    }
                ],
                labelLine: {
                    show: true,
                    length: 5,
                    length2: 7,
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 2,
                }
            },
            {
                name: '注册类型分析',
                type: 'pie',
                radius: ['11%','23%'],
                center: ['44%', '53%'],
                avoidLabelOverlap: true,
                hoverOffset: 8,
                data: [
                    {
                        value: 35,
                        name: '企业',
                        itemStyle: {
                            color: "#8b86f1"
                        }
                    },
                    {
                        value: 65,
                        name: '个人',
                        itemStyle: {
                            color: "#57aae1"
                        }
                    }
                ],
                labelLine: {
                    show: true,
                    length: 5,
                    length2: 5,
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 2,
                }
            },
        ]
    };

    myChart.setOption(option);
    return myChart;
};

function chart02(id) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: "center",
            y: "15",
            itemHeight: 10,
            itemWidth: 16,
            textStyle: {
                fontSize: 13,
                color: "#666666"
            },
            data: [
                {
                    name: "访问量"
                },
                {
                    name: "下载量"
                },
                {
                    name: "调用量"
                }
            ]
        },
        grid: {
            top:55,
            left: '40',
            right: '40',
            bottom: '15',
            containLabel: true
        },
        symbolSize: 10,
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                interval: 0,
                margin: 14,
                textStyle: {
                    color: '#666666',
                    fontSize: 13,
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: "#e4e9ec",
                    type: 'dotted'
                }
            },
            data: ['2018-12', '2019-02', '2019-04', '2019-06', '2019-08', '2019-10']
        },
        yAxis: [
            {
                type: 'value',
                name: "",
                nameLocation: 'end',
                nameTextStyle: {
                    color: '#627da3',
                    fontSize: 12,
                },
                nameGap: 15,
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: "#e4e9ec",
                        type: 'dotted'
                    }
                },
                axisTick: {
                    show: false
                },
                splitLine: {
                    lineStyle: {
                        color: "#d3d9dd",
                        type:'dotted'
                    }
                },
                splitArea: {
                    show: false,
                    areaStyle: {
                        color: ['#fbfcfd', '#fff']
                    }
                },
                axisLabel: {
                    formatter: "{value}万",
                    margin: 14,
                    textStyle: {
                        color: '#666666',
                        fontSize: 14,
                    }
                }
            }
        ],
        color: ['#20c262'],
        series: [
            {
                name: '访问量',
                type: 'line',
                smooth: false,
                symbolSize: 10,
                itemStyle: {
                    color: "#8b86f1"
                },
                areaStyle: {normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(139,134,241,.15)'
                        },
                            {
                                offset: 1,
                                color: 'rgba(139,134,241,.01)'
                            }
                        ], false),
                }},
               
                data: [400, 320, 420, 480, 410, 450]
            },
            {
                name: '下载量',
                type: 'line',
                smooth: false,
                symbolSize: 8,
                itemStyle: {
                    color: "#2099f0"
                },
                areaStyle: {normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(32,153,240,.15)'
                        },
                            {
                                offset: 1,
                                color: 'rgba(32,153,240,.01)'
                            }
                        ], false),
                }},
                data: [220, 280, 250, 330, 300, 230]
            },
            {
                name: '调用量',
                type: 'line',
                smooth: false,
                symbolSize: 8,
                itemStyle: {
                    color: "#35baa4"
                },
                areaStyle: {normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(53,186,164,.15)'
                        },
                            {
                                offset: 1,
                                color: 'rgba(53,186,164,.01)'
                            }
                        ], false),
                }},
                data: [320, 480, 150, 310, 320, 330]
            }
        ]
    };
    myChart.setOption(option);
    return myChart;
};

function chart03(id) {
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        tooltip: {},
        series: [ {
            type: 'wordCloud',
            gridSize: 4,
            sizeRange: [12, 40],
            rotationRange: [-90, 90,-90,90,-90,90],
            shape: 'pentagon',
            width: 600,
            height: 400,
            drawOutOfBound: true,
            data: [
                {
                    name: '惠民服务',
                    value: 10000,
                    textStyle: {
                        normal: {
                            color: '#5b89df'
                        },
                    }
                },
                {
                    name: '经济管理',
                    value: 6181,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                    }
                },
                {
                    name: '公共安全',
                    value: 4386,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                    }
                },
                {
                    name: '文化 卫生 体育',
                    value: 4055,
                    textStyle: {
                        normal: {
                            color: '#41b0dd'
                        },
                    }
                },
                {
                    name: '服务支付方式',
                    value: 1112,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                    }
                },
                {
                    name: '企业服务',
                    value: 2244,
                    textStyle: {
                        normal: {
                            color: '#385c8f'
                        },
                    }
                },
                {
                    name: '经济管理',
                    value: 1898,
                    textStyle: {
                        normal: {
                            color: '#5ce2ef'
                        },
                    }
                },
                {
                    name: '政府资源管理',
                    value: 1484,
                    textStyle: {
                        normal: {
                            color: '#fca626'
                        },
                    }
                },
                {
                    name: '财政',
                    value: 1112,
                    textStyle: {
                        normal: {
                            color: '#73b3f0'
                        },
                    }
                },
                {
                    name: '政府资源管理',
                    value: 965,
                    textStyle: {
                        normal: {
                            color: '#fca626'
                        },
                    }
                },
                {
                    name: '公共安全',
                    value: 847,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                    }
                },
                {
                    name: '经济管理',
                    value: 582,
                    textStyle: {
                        normal: {
                            color: '#5ce2ef'
                        },
                    }
                },
                {
                    name: '文化 卫生 体育',
                    value: 555,
                    textStyle: {
                        normal: {
                            color: '#41b0dd'
                        },
                    }
                },
                {
                    name: '服务支付方式',
                    value: 550,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                    }
                }
            ]
        } ]
    };
    myChart.setOption(option);
    return myChart;
}

function chart04(id) {
    var myChart = echarts.init(document.getElementById(id));
    var data = [4194, 2637, 1885, 1580, 1552, 1407, 1197, 1142, 1103, 1002]

    var dataShadow = [];
    var xMax = 5000;
    for (var i = 0; i < data.length; i++) {
        dataShadow.push({
            value: xMax,
            value2: data[i]
        });
    };

    option = {
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "shadow"
            }
        },
        grid: {
            containLabel: true,
            left: 14,
            right: 52,
            bottom: 12,
            top: 12
        },
        xAxis: {
            type: "value",
            min: 0,
            max: xMax,
            boundaryGap: true,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false,
            },
            splitLine: {
                show: false,
            },
            axisLine: {
                show: false,
            },
        },
        yAxis: {
            type: "category",
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "#dee9f0",
                }
            },
            axisLabel: {
                interval: 0,
                color: "#333",
                fontSize: 13,
            },
            data: ["四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心"]
        },
        color: "#43bcf4",
        series: [
            {
                type: "bar",
                barWidth: 15,
                label: {
                    show: true,
                    position: "right",
                    fontSize: 13,
                    color: "#357ea0",
                    padding: [0, 0, 0, 10],
                    formatter: function (params) {
                        //console.log(params.data.value2);
                        return params.data.value2
                    }

                },
                itemStyle: {
                    barBorderRadius: [0, 2, 2, 0],
                    color: {
                        type: 'linear',
                        x: 1,
                        y: 0,
                        x2: 0,
                        y2: 0,
                        colorStops: [{
                            offset: 1, color: '#7189d5' // 0% 处的颜色
                        }, {
                            offset: 0, color: '#6c9cf1' // 100% 处的颜色
                        }],
                        global: false // 缺省为 false
                    }
                },
                data: data.reverse(),
            }
        ]
    };
    myChart.setOption(option);
    return myChart;
};

function chart05(id) {
    var myChart = echarts.init(document.getElementById(id));
    var data = [4194, 2637, 1885, 1580, 1552, 1407, 1197, 1142, 1103, 1002]

    var dataShadow = [];
    var xMax = 5000;
    for (var i = 0; i < data.length; i++) {
        dataShadow.push({
            value: xMax,
            value2: data[i]
        });
    };

    option = {
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "shadow"
            }
        },
        grid: {
            containLabel: true,
            left: 14,
            right: 52,
            bottom: 12,
            top: 12
        },
        xAxis: {
            type: "value",
            min: 0,
            max: xMax,
            boundaryGap: true,
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false,
            },
            splitLine: {
                show: false,
            },
            axisLine: {
                show: false,
            },
        },
        yAxis: {
            type: "category",
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "#dee9f0",
                }
            },
            axisLabel: {
                interval: 0,
                color: "#333",
                fontSize: 13,
            },
            data: ["四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心", "四川省卫生信息中心"]
        },
        color: "#43bcf4",
        series: [
            {
                type: "bar",
                barWidth: 15,
                label: {
                    show: true,
                    position: "right",
                    fontSize: 13,
                    color: "#357ea0",
                    padding: [0, 0, 0, 10],
                    formatter: function (params) {
                        //console.log(params.data.value2);
                        return params.data.value2
                    }

                },
                itemStyle: {
                    barBorderRadius: [0, 2, 2, 0],
                    color: {
                        type: 'linear',
                        x: 1,
                        y: 0,
                        x2: 0,
                        y2: 0,
                        colorStops: [{
                            offset: 1, color: '#7dbbfb' // 0% 处的颜色
                        }, {
                            offset: 0, color: '#9ce5fd' // 100% 处的颜色
                        }],
                        global: false // 缺省为 false
                    }
                },
                data: data.reverse(),
            }
        ]
    };
    myChart.setOption(option);
    return myChart;
};

$(function () {

    var chartInit01 = chart01("chart01");
    var chartInit02 = chart02("chart02");
    var chartInit03 = chart03("chart03");
    var chartInit04 = chart04("chart04");
    var chartInit05 = chart05("chart05");


    $(window).resize(function () {
        chartInit01.resize();
        chartInit02.resize();
        chartInit03.resize();
        chartInit04.resize();
        chartInit05.resize();
    });
});