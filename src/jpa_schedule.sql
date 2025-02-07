-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS `jpa_schedule`;
USE jpa_schedule;

-- 데이터베이스 유저 생성
CREATE USER IF NOT EXISTS `sa`@`localhost` IDENTIFIED BY 'sparta';
CREATE USER IF NOT EXISTS `sa`@`%` IDENTIFIED BY 'sparta';
GRANT ALL PRIVILEGES ON `jpa_schedule`.* TO `sa`@`localhost`;
GRANT ALL PRIVILEGES ON `jpa_schedule`.* TO `sa`@`%`;

-- 테이블 생성 (유저)
CREATE TABLE member
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '유저 식별자',
    username VARCHAR(100) NOT NULL COMMENT '이름',
    password BLOB NOT NULL COMMENT '비밀번호',
    email VARCHAR(100) COMMENT '이메일',
    created_at TIMESTAMP NOT NULL COMMENT '등록일',
    updated_at TIMESTAMP NOT NULL COMMENT '수정일',
    deleted_at TIMESTAMP COMMENT '삭제일'
);

-- 테이블 생성 (일정)
CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    member_id BIGINT NOT NULL COMMENT '작성자',
    title VARCHAR(1000) NOT NULL COMMENT '할일 제목',
    contents VARCHAR(1000) COMMENT '할일 내용',
    created_at TIMESTAMP NOT NULL COMMENT '등록일',
    updated_at TIMESTAMP NOT NULL COMMENT '수정일',
    deleted_at TIMESTAMP COMMENT '삭제일',
    FOREIGN KEY (member_id) REFERENCES member(id)
);
