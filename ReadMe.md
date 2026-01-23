第 1 天：项目骨架 + 数据库连通
•	包结构定型：domain / application / infrastructure
•	MyBatis + MySQL 能连上、能跑一条简单查询
•	统一异常/日志（你已有也行，先能看日志就够）

验收：启动不报错，能打印启动日志。

⸻

第 2 天：Kline 数据模型 + kline 表 + MyBatis
•	建 kline 表（你之前那套：market_type + base + quote + interval_sec + open_time 唯一键）
•	KlineMapper：
•	upsert
•	findRange（取最近 60 根 1m）
•	PO/Assembler：PO ↔ domain Kline

验收：能插入几根K线并查询出来。

⸻

第 3 天：事件总线 + KlineClosedEvent 闭环
•	EventBus（最简同步版）或 Spring Event（二选一）
•	KlineIngestService：保存 Kline 后触发 KlineClosedEvent（只在 closed=1 的时候发）
•	打日志：KlineClosedEvent published: BTCUSDT 1m openTime=...

验收：每插入一根 closed K，都能触发事件日志。

⸻

第 4 天：策略框架（Strategy + Runner）
•	Strategy 接口：
•	Optional<Signal> onKlineClosed(KlineClosedEvent e)
•	StrategyRunner：订阅事件 → 调策略 → 输出 signal 日志
•	Signal 模型（symbol、direction、reason、ts）

验收：事件到来时，策略可以返回一个 signal 并打印出来。

⸻

第 5 天：实现策略3（近 1 小时暴跌反弹）

策略定义（P0版，够用）
•	使用 1m Kline
•	取最近 60 根：
•	close_now（最新收盘价）
•	close_60m_ago（60 根前收盘价）
•	跌幅：
•	dropPct = (close_now - close_60m_ago) / close_60m_ago
•	触发条件（例子，可调）：
•	dropPct <= -0.05（1小时跌 >= 5%）
•	且币种在白名单：BTC/ETH
•	“反弹确认”（非常简单，避免纯抄底）：
•	最近 3 根里出现 2 根收阳（close > open）
•	或 close_now > EMA(10)（P1再加）

验收：给它一段人工构造的暴跌数据，能触发信号；正常波动不触发。

⸻

第 6 天：可回放（Replay）+ 可测试
•	做一个 ReplayRunner：
•	从 DB 按时间顺序读 kline（最近 N 根）
•	逐条 publish KlineClosedEvent
•	做 3 个测试用例（哪怕是 main 方法也行）：
•	暴跌触发
•	不暴跌不触发
•	暴跌后冷却期不重复触发（P1）

验收：同一批历史数据回放，输出信号稳定一致。

⸻

第 7 天：收尾（把它变成“能用的系统”）
•	signal 落库（可选，但强烈建议）：
•	signal 表：symbol、market_type、strategy_id、direction、score、reason、ts
•	输出一份总结日志：
•	本次回放触发几次、命中哪些币种、平均跌幅
•	把阈值参数化（yml 配置）：
•	dropThresholdPct、cooldownMinutes、symbols

验收：你能对着配置调参，系统行为可控。