create database shipment;
set search_path = shipment;

create table shipment_event
(
    event_id     uuid primary key,
    event_type   varchar(255),
    shipment_id  uuid,
    version      int,
    occurred_at  timestamp,
    payload      bytea,
    payload_json jsonb -- Event data stored as JSON, todo: temporary - remove
);