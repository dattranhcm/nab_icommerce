:: go and build discovery-service-server
cd ../
cd discovery-service-server\target
call java -jar discovery-service-server-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../

:: go and build product service
cd product-service\target
call java -jar product-service-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../

:: go and build customer service
cd customer-service\target
call java -jar customer-service-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../

:: go and build shopping cart service
cd shoppingcart-service\target
call java -jar shoppingCart-service-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../

:: go and build order service
cd purchase-order-service\target
call java -jar purchaseOrder-service-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../

:: go and build gateway service
cd gateway\target
call java -jar gateway-service-0.0.1-SNAPSHOT.jar fully.qualified.package.Application 
cd ../