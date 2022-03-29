from django.urls import path
from . import views

urlpatterns = [
    path('', views.PerformanceSimilarity.as_view())
]
