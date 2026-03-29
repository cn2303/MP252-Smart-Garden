CREATE TYPE device_type_enum AS ENUM ('TEMPERATURE', 'MOISTURE', 'LIGHT', 'MULTI_SENSOR');
CREATE TYPE device_status_enum AS ENUM ('ONLINE', 'OFFLINE');

CREATE TYPE sensor_type_enum AS ENUM ('TEMPERATURE', 'MOISTURE', 'LIGHT', 'HUMIDITY');

CREATE TYPE pump_status_enum AS ENUM ('ON', 'OFF');

CREATE TYPE pump_action_enum AS ENUM ('ON', 'OFF');
CREATE TYPE pump_mode_enum AS ENUM ('AUTO', 'MANUAL');
CREATE TYPE pump_log_status_enum AS ENUM ('SUCCESS', 'FAILED');

CREATE TYPE alert_type_enum AS ENUM ('TEMPERATURE', 'MOISTURE', 'LIGHT');

-- 1. Bảng User
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Bảng Phone
CREATE TABLE phones (
    phone_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    phone_name VARCHAR(255),
    token VARCHAR(255),
	
    CONSTRAINT fk_phone_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 3. Bảng Connection
CREATE TABLE connections (
    conn_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    broker_name VARCHAR(255),
	feed VARCHAR(255),
    password VARCHAR(255),
    addr_broker VARCHAR(255),
	
    CONSTRAINT fk_conn_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 4. Bảng Pump và Configuration
CREATE TABLE pumps (
    pump_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    conn_id INT NOT NULL,
    name VARCHAR(255),
    status pump_status_enum,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    temperature_max FLOAT,
    temperature_min FLOAT,
    light_intensity_max FLOAT,
    moisture_threshold FLOAT,
    field_capacity FLOAT,
    root_depth FLOAT,
    area FLOAT,
    
    CONSTRAINT fk_pump_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    CONSTRAINT fk_pump_conn FOREIGN KEY (conn_id) REFERENCES connections(conn_id) ON DELETE SET NULL,
	CONSTRAINT chk_pump_config
		CHECK (
		    temperature_min <= temperature_max AND
		    moisture_threshold BETWEEN 0 AND 100 AND
		    field_capacity >= 0 AND
		    root_depth > 0 AND
		    area > 0 AND
		    light_intensity_max >= 0
);

-- 5. Bảng PumpLog
CREATE TABLE pump_logs (
    log_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    pump_id INT NOT NULL,
    action pump_action_enum,
    mode pump_mode_enum,
    water_volume FLOAT,
    status pump_log_status_enum,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    CONSTRAINT fk_log_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    CONSTRAINT fk_log_pump FOREIGN KEY (pump_id) REFERENCES pumps(pump_id) ON DELETE CASCADE
);

-- 6. Bảng Device
CREATE TABLE devices (
    device_id SERIAL PRIMARY KEY,
    pump_id INT NOT NULL,
    conn_id INT NOT NULL,
    name VARCHAR(255),
    type device_type_enum,
    status device_status_enum,
    last_seen TIMESTAMP,
	
    CONSTRAINT fk_device_pump FOREIGN KEY (pump_id) REFERENCES pumps(pump_id) ON DELETE SET NULL,
    CONSTRAINT fk_device_conn FOREIGN KEY (conn_id) REFERENCES connections(conn_id) ON DELETE SET NULL
);

-- 7. Bảng SensorData
CREATE TABLE sensor_data (
    data_id SERIAL PRIMARY KEY,
    device_id INT NOT NULL,
    value FLOAT,
    type sensor_type_enum,
    unit VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    CONSTRAINT fk_sensor_device FOREIGN KEY (device_id) REFERENCES devices(device_id) ON DELETE CASCADE,
	CONSTRAINT chk_sensor_value_by_type 
		CHECK (
		    (type = 'TEMPERATURE' AND value BETWEEN -50 AND 100) OR
		    (type = 'MOISTURE' AND value BETWEEN 0 AND 100) OR
		    (type = 'LIGHT' AND value >= 0) OR
		    (type = 'HUMIDITY' AND value BETWEEN 0 AND 100)
);

-- 8. Bảng Alert
CREATE TABLE alerts (
    alert_id SERIAL PRIMARY KEY,
    device_id INT NOT NULL,
    type alert_type_enum,
    value FLOAT,
    threshold FLOAT,
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    CONSTRAINT fk_alert_device FOREIGN KEY (device_id) REFERENCES devices(device_id) ON DELETE CASCADE,
	CONSTRAINT chk_alert_value_threshold
		CHECK (
		    (type = 'TEMPERATURE' AND value BETWEEN -50 AND 100 AND threshold BETWEEN -50 AND 100) OR
		    (type = 'MOISTURE' AND value BETWEEN 0 AND 100 AND threshold BETWEEN 0 AND 100) OR
		    (type = 'LIGHT' AND value >= 0 AND threshold >= 0)
);