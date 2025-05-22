# Spring Boot Kubernetes Autoscaler (`instancePOC`)

[![Java 17](https://img.shields.io/badge/Java-17-red)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-green)](https://spring.io/projects/spring-boot)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-1.29-blue)](https://kubernetes.io/)

A production-ready demo of Horizontal Pod Autoscaling (HPA) in Kubernetes with Spring Boot. Includes:
- CPU stress testing endpoint (`/stress`)
- Resource limits for safe local development
- Metrics Server and CoreDNS configurations

## 🚀 Quick Start

### 1. Build Docker Image
```bash
docker build -t instancepoc:stable .

### 2. Deploy to Kubernetes
# Apply configurations (Deployment + HPA)
kubectl apply -f deployment.yaml
kubectl apply -f hpa.yaml
# Verify deployment (wait 2-3 minutes)
kubectl get pods -w

### 3. Test Autoscaling
# Generate controlled load (PowerShell)
1..20 | ForEach-Object {
    Invoke-WebRequest -Uri "http://localhost:9090/stress" -UseBasicParsing
    Start-Sleep -Seconds 1
}
# Monitor scaling
kubectl get hpa -w

📊 Expected Output
# Pod status
NAME                          READY   STATUS    RESTARTS   AGE
instancepoc-77d6448784-t678m   1/1     Running   0          2m
# HPA status
NAME              REFERENCE                TARGETS   MINPODS   MAXPODS   REPLICAS
instancepoc-hpa   Deployment/instancepoc   25%/30%   1         2         2

Configuration Files
File	                Purpose	                      Key Settings
deployment.yaml	        Main app deployment	          resources: 200m CPU, 256Mi memory
hpa.yaml	            Autoscaling rules	          cpu: 30%, maxReplicas: 2
metrics-server.yaml	    Cluster metrics collection	  --metric-resolution=15s
